package com.bside.starterapi.api.domain.post;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

@Component
public class PostEventHandler {

    @EventListener
    @Transactional
    public void handle(TitleUpdatedEvent event) {
        System.out.println("타이틀이 수정되었습니다. 변경된 타이틀: " + event.getPost().getTitle());
    }
}
