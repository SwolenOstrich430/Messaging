package com.app.Message_Backend.publishers;

import com.app.Message_Backend.pojo.Message;
import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.observables.ConnectableObservable;
import org.springframework.stereotype.Component;


@Component
public class MessagePublisher {
    private Long conversationId;
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

    public void publish(final Message createdMessage) {
        if(conversationId.equals(createdMessage.getConversationId())) {
            emitter.onNext(createdMessage);
        }
    }


    public Flowable<Message> getPublisher(Long conversationId) {
        setConversationId(conversationId);
        return publisher;
    }

    public void setConversationId(Long conversationId) {
        this.conversationId = conversationId;
    }
}
