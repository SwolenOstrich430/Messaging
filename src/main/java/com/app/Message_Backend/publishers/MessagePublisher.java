package com.app.Message_Backend.publishers;

import com.app.Message_Backend.auth.AuthorizationContext;
import com.app.Message_Backend.entities.Conversation;
import com.app.Message_Backend.entities.Message;
import graphql.schema.DataFetchingEnvironment;
import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.observables.ConnectableObservable;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class MessagePublisher {
    private final Flowable<Message> publisher;

    private ObservableEmitter<Message> emitter;

    public MessagePublisher() {
        Observable<Message> commentUpdateObservable = Observable.create(emitter -> {
            this.emitter = emitter;
        });

        ConnectableObservable<Message> connectableObservable = commentUpdateObservable.share().publish();
        connectableObservable.connect();

        publisher = connectableObservable.toFlowable(BackpressureStrategy.BUFFER);
    }

    public void publish(final Message createdMessage, AuthorizationContext authContext) {
        System.out.println(authContext.getUser().getId());
        System.out.println(authContext.getUser().getConversations());
        List<Conversation> conversations = authContext.getUser().getConversations();
        Long sentMessageConvId = createdMessage.getConversationId();

        if(conversations.contains(sentMessageConvId)) {
            emitter.onNext(createdMessage);
        }
    }


    public Flowable<Message> getPublisher() {
        return publisher;
    }

}
