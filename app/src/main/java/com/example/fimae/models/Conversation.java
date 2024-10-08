package com.example.fimae.models;

import androidx.annotation.IntDef;
import androidx.annotation.StringDef;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.ServerTimestamp;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.*;

public class Conversation {
    public static final String FRIEND_CHAT = "friend-chat";
    public static final String GROUP_CHAT = "group-chat";
    private String id;
    private String blockedBy;
    @ServerTimestamp
    private Date createdAt;
    private DocumentReference lastMessage;
    ArrayList<String> participantIds = new ArrayList<> ();
    HashMap<String, Date> readLastMessageAt = new HashMap<>();
    HashMap<String, Date> joinedAt = new HashMap<>();

    public Conversation(){

    }
    private Date timestamp;

    // Các getter và setter hiện tại

    // Thêm getter và setter cho timestamp
    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }
    public static Conversation create(String id, String type, ArrayList<String> participantIds){
        Conversation conversation = new Conversation();
        conversation.setId(id);
        Collections.sort(participantIds);
        conversation.setParticipantIds(participantIds);
        return conversation;
    }

    public HashMap<String, Date> getReadLastMessageAt() {
        return readLastMessageAt;
    }

    public void setReadLastMessageAt(HashMap<String, Date> readLastMessageAt) {
        this.readLastMessageAt = readLastMessageAt;
    }

    public HashMap<String, Date> getJoinedAt() {
        return joinedAt;
    }

    public void setJoinedAt(HashMap<String, Date> joinedAt) {
        this.joinedAt = joinedAt;
    }

    public ArrayList<String> getParticipantIds() {
        return participantIds;
    }

    public void setParticipantIds(ArrayList<String> participantIds) {
        this.participantIds = participantIds;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBlockedBy() {
        return blockedBy;
    }

    public void setBlockedBy(String blockedBy) {
        this.blockedBy = blockedBy;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }


    public DocumentReference getLastMessage() {
        return lastMessage;
    }

    public void setLastMessage(DocumentReference lastMessage) {
        this.lastMessage = lastMessage;
    }

    public String getOtherParticipantId(){
        String myId = FirebaseAuth.getInstance().getCurrentUser().getUid();
        for (String participantId : participantIds) {
            if(!participantId.equals(myId)){
                return participantId;
            }
        }
        return null;
    }
}
