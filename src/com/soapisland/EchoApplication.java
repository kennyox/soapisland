/*
 * Copyright 2016 LINE Corporation
 *
 * LINE Corporation licenses this file to you under the Apache License,
 * version 2.0 (the "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at:
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations
 * under the License.
 */

package com.soapisland;

import static java.util.Collections.singletonList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.support.SpringBootServletInitializer;

import com.linecorp.bot.client.LineMessagingClient;
import com.linecorp.bot.model.ReplyMessage;
import com.linecorp.bot.model.event.Event;
import com.linecorp.bot.model.event.MessageEvent;
import com.linecorp.bot.model.event.message.TextMessageContent;
import com.linecorp.bot.model.message.TextMessage;
import com.linecorp.bot.model.response.BotApiResponse;
import com.linecorp.bot.spring.boot.annotation.EventMapping;
import com.linecorp.bot.spring.boot.annotation.LineMessageHandler;

@SpringBootApplication
@LineMessageHandler
public class EchoApplication extends SpringBootServletInitializer {
    @Autowired
    private LineMessagingClient lineMessagingClient;

    public static void main(String[] args) {
	System.out.println("start");
	System.setProperty("line.bot.channelToken", "ieXObTETz9czzwwMkY/Ki1GgDm+Hve4+iYEcain/TpwA7WQsvA4mCntqqYm10wkC89fTy/3A92IgUduJrYDy3t7W7zZ3EwVO0xM97ZxY5FZvkskmzH/GpFQLTz3I76qAK2m/Cx024aVNbIj+TM5Q1gdB04t89/1O/w1cDnyilFU=");
	System.setProperty("line.bot.channelSecret", "dd1b90e8c58eba18af65e2870b43621d");
	SpringApplication.run(EchoApplication.class, args);
    }

    @EventMapping
    public void handleTextMessageEvent(MessageEvent<TextMessageContent> event) throws Exception {
	System.out.println("event: " + event);
	final BotApiResponse apiResponse = lineMessagingClient.replyMessage(
		new ReplyMessage(event.getReplyToken(), singletonList(new TextMessage(event.getMessage().getText()))))
		.get();
	System.out.println("Sent messages: " + apiResponse);
    }

    @EventMapping
    public void defaultMessageEvent(Event event) {
	System.out.println("event: " + event);
    }
}
