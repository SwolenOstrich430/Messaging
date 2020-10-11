package com.app.Message_Backend.publishers;

import com.app.Message_Backend.entities.Conversation;
import com.app.Message_Backend.entities.User;
import com.app.Message_Backend.service.UserService;
import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.observables.ConnectableObservable;
import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.awt.desktop.SystemSleepEvent;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;

@Component
public class ConversationPublisher implements Publisher<Conversation> {
    @Autowired
    private UserService userService;
    private final Map<Long, ConversationSubscription> subscriptions = new ConcurrentHashMap<Long, ConversationSubscription>();
    private ExecutorService executor = Executors.newFixedThreadPool(4);

    @Override
    public void subscribe(Subscriber<? super Conversation> subscriber) {
        System.out.println("got in subscribe method");
        Optional<User> potentialUser = Optional.of(userService.getUserFromContext());
        if(!potentialUser.isPresent()) return;
        System.out.println("user was there");
        ConversationSubscription newSubscription = new ConversationSubscription(executor, subscriber,
                potentialUser.get().getId());
        subscriptions.put(newSubscription.getUserId(), newSubscription);
        System.out.println(subscriptions.size());
        subscriber.onSubscribe(newSubscription);
    }

    public void publishConversation(Conversation conversation) {
        System.out.println("in publish conversation");
        Set<User> recipients = conversation.getUsers();
        for(User user : recipients) {
            System.out.println("user: " + user.getUsername());
            ConversationSubscription potentialSub = subscriptions.get(user.getId());
            System.out.println("subscription is null: " + potentialSub == null);
            if(potentialSub == null) continue;
            System.out.println("got past null check");
            potentialSub.getSubscriber().onNext(conversation);
        }
    }

    private class ConversationSubscription implements Subscription {
        private final ExecutorService executor;
        private Subscriber<? super Conversation> subscriber;
        private AtomicBoolean isCanceled;
        private Long userId;

        public ConversationSubscription(ExecutorService executor, Subscriber<? super Conversation> subscriber, Long userId) {
            this.executor = executor;
            this.subscriber = subscriber;
            this.isCanceled = new AtomicBoolean(false);
            this.userId = userId;
        }


        public void request(Long n, Conversation conversation) {
            if (isCanceled.get()) {
                return;
            }

            publishConversation(conversation);
        }

        @Override
        public void request(long l) {
            System.out.println(l);
            System.out.println("in request method with just long");
        }

        @Override
        public void cancel() {
            isCanceled.set(true);
            subscriptions.remove(userId);
        }

        public ExecutorService getExecutor() {
            return executor;
        }

        public Subscriber<? super Conversation> getSubscriber() {
            return subscriber;
        }

        public AtomicBoolean getIsCanceled() {
            return isCanceled;
        }

        public Long getUserId() {
            return userId;
        }
    }
//    private final int numThreads = 10;
//    private Flowable<Conversation> publisher;
//    private ObservableEmitter<Conversation> emitter;

//    public ConversationPublisher() {
//        Observable<Conversation> createdConversationObservable = Observable.create(emitter -> {
//            this.emitter = emitter;
//        });
//
//        ConnectableObservable<Conversation> connectableObservable = createdConversationObservable.share().publish();
//        connectableObservable.connect();
//
//        publisher = connectableObservable.toFlowable(BackpressureStrategy.BUFFER);
//    }
//
//    public void onConversationCreated(Conversation conversation) {
//        System.out.println("got in onCreatedConversation in publisher");
//        try {
//            for(User user : conversation.getUsers()) {
//                System.out.println("user in conversation: " + user.getUsername());
//            }
//            emitter.onNext(conversation);
//            System.out.println("emitter ran");
//        } catch (RuntimeException e) {
//            e.printStackTrace();
//            System.out.println(e.getMessage());
//        }
//    }
//
//    public Flowable<Conversation> getPublisher(User user) {
//        System.out.println("user id that should correspond to username: " + user.getId());
//        return publisher.filter(conversation -> conversation.getUsers().contains(user));
//    }

}
