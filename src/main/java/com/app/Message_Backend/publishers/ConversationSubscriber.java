package com.app.Message_Backend.publishers;

import com.app.Message_Backend.entities.Conversation;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

public class ConversationSubscriber implements Subscriber<Conversation> {
    @Override
    public void onSubscribe(Subscription subscription) {

    }

    @Override
    public void onNext(Conversation conversation) {
        System.out.println("got in on next");
        System.out.println("conversation: " + conversation);
    }

    @Override
    public void onError(Throwable throwable) {

    }

    @Override
    public void onComplete() {

    }
}
