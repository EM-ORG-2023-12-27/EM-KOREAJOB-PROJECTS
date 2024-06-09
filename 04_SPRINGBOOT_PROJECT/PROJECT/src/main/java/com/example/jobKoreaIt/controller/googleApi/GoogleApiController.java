package com.example.jobKoreaIt.controller.googleApi;


import com.fasterxml.jackson.core.JsonFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.List;

@Controller
@Slf4j
@RequestMapping("/google")
public class GoogleApiController {
    @GetMapping("/mail/main")
    public void main(){
        log.info("GET /google/mail/main....");
    }

    @Autowired
    JavaMailSender javaMailSender;

    @GetMapping("/mail/req")
    public String req(@RequestParam("email") String email, @RequestParam("text") String text){
        log.info("GET /google/mail/req....email : " + email + " text : "+ text);
        log.info("javaMailSender : " + javaMailSender);

        //메시지 지정
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject("[메일테스트]");
        message.setText(text);

        javaMailSender.send(message);

        return "redirect:/google/mail/main?message=Success";
    }
}

//public class GoogleApiController {
//private static final String APPLICATION_NAME = "My Mail API Application";
//private static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();
//private static final String TOKENS_DIRECTORY_PATH = "tokens";
//private static final List<String> SCOPES = Collections.singletonList(GmailScopes.GMAIL_READONLY);
//private static final String CREDENTIALS_FILE_PATH = "/path/to/credentials.json";
//
//private static Credential getCredentials(final NetHttpTransport HTTP_TRANSPORT) throws IOException {
//    InputStream in = GoogleApiController.class.getResourceAsStream(CREDENTIALS_FILE_PATH);
//    GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(JSON_FACTORY, new InputStreamReader(in));
//
//    GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
//            HTTP_TRANSPORT, JSON_FACTORY, clientSecrets, SCOPES)
//            .setDataStoreFactory(new FileDataStoreFactory(new java.io.File(TOKENS_DIRECTORY_PATH)))
//            .setAccessType("offline")
//            .build();
//
//    LocalServerReceiver receiver = new LocalServerReceiver.Builder().setPort(8888).build();
//    return new AuthorizationCodeInstalledApp(flow, receiver).authorize("user");
//}
//
//@GetMapping("/findId")
//public String findId(@RequestParam String email) throws IOException, GeneralSecurityException {
//    final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
//    Gmail service = new Gmail.Builder(HTTP_TRANSPORT, JSON_FACTORY, getCredentials(HTTP_TRANSPORT))
//            .setApplicationName(APPLICATION_NAME)
//            .build();
//
//    String user = "me";
//    ListMessagesResponse messagesResponse = service.users().messages().list(user).setQ("from:" + email).execute();
//    List<Message> messages = messagesResponse.getMessages();
//
//    if (messages == null || messages.isEmpty()) {
//        return "ID not found.";
//    } else {
//        return "ID found: " + messages.get(0).getId();
//    }
//}
//}
