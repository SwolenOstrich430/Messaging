package com.app.Message_Backend.publishers;

import com.app.Message_Backend.dto.MessageDTO;
import com.app.Message_Backend.dto.MessagesFactory;
import com.app.Message_Backend.entities.Conversation;
import com.app.Message_Backend.entities.Message;
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

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;


@Component
public class MessagePublisher implements Publisher<Message> {
    @Autowired
    private UserService userService;
    private Map<Long, MessageSubscription> subscriptions = new HashMap<Long, MessageSubscription>();
    private ExecutorService executor = Executors.newFixedThreadPool(4);

    @Override
    public void subscribe(Subscriber<? super Message> subscriber) {
        System.out.println("got in subscribe method");
        Optional<User> potentialUser = Optional.of(userService.getUserFromContext());
        if(!potentialUser.isPresent()) return;
        System.out.println("user was there");
        MessageSubscription newSubscription = new MessageSubscription(executor, subscriber,
                potentialUser.get().getId());
        subscriptions.put(newSubscription.getUserId(), newSubscription);
        System.out.println(subscriptions.size());
        subscriber.onSubscribe(newSubscription);
    }

    public void publishMessage(Message messageToPublish) {
        System.out.println("in publish message");
        Set<User> recipients = messageToPublish.getConversation().getUsers();
        for(User user : recipients) {
            System.out.println("user: " + user.getUsername());
            MessageSubscription potentialSub = subscriptions.get(user.getId());
            System.out.println("subscription is null: " + potentialSub == null);
            if(potentialSub == null) continue;
            System.out.println("got past null check");
            potentialSub.getSubscriber().onNext(messageToPublish);
        }
    }

    private class MessageSubscription implements Subscription {
        private final ExecutorService executor;
        private Subscriber<? super Message> subscriber;
        private AtomicBoolean isCanceled;
        private Long userId;

        public MessageSubscription(ExecutorService executor, Subscriber<? super Message> subscriber, Long userId) {
            this.executor = executor;
            this.subscriber = subscriber;
            this.isCanceled = new AtomicBoolean(false);
            this.userId = userId;
        }


        public void request(Long n, Message message) {
            if (isCanceled.get()) {
                return;
            }

            publishMessage(message);
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

        public Subscriber<? super Message> getSubscriber() {
            return subscriber;
        }

        public AtomicBoolean getIsCanceled() {
            return isCanceled;
        }

        public Long getUserId() {
            return userId;
        }
    }
}
