package com.example.apimaroma.user;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.Query;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.UserRecord;
import com.google.firebase.cloud.FirestoreClient;

import org.springframework.stereotype.Service;

@Service
public class UserService {
    private Firestore dbFirestore = FirestoreClient.getFirestore();
    private CollectionReference usersTable = dbFirestore.collection("users");

    public UserBean testGetUser() throws ExecutionException, InterruptedException {
        
        // CollectionReference usersTable = dbFirestore.collection("users")
        DocumentReference docRef = usersTable.document("DYo4xs5SuwRNWAN2GMuiLkRGLzp2");
        ApiFuture<DocumentSnapshot> future = docRef.get();
        DocumentSnapshot document = future.get();
        UserBean user = document.toObject(UserBean.class);
        return user;
    }
    public UserBean getUser(String userId) throws ExecutionException, InterruptedException{
        DocumentReference documentReference = usersTable.document(userId);
        ApiFuture<DocumentSnapshot> future = documentReference.get();
        DocumentSnapshot document = future.get();
        UserBean user = document.toObject(UserBean.class);
        return user;
    }
    public List<UserBean> searchUserByName(String name) throws ExecutionException, InterruptedException{
        // Query query = usersTable.orderBy("name").startAt(name).endAt(name+'\uf8ff');
        Query query1 = usersTable.whereEqualTo("firstName", name);
        Query query2 = usersTable.whereEqualTo("lastName", name);
        ApiFuture<QuerySnapshot> querySnapshot1 = query1.get();
        ApiFuture<QuerySnapshot> querySnapshot2 = query2.get();

        List<UserBean> usersArray = new ArrayList<>();
        for (DocumentSnapshot document : querySnapshot1.get().getDocuments()) {
            usersArray.add(document.toObject(UserBean.class));
        }
        for (DocumentSnapshot document : querySnapshot2.get().getDocuments()) {
            UserBean tempUser = document.toObject(UserBean.class);
            if(!usersArray.contains(tempUser)){
                usersArray.add(tempUser);
            }
        }
        return usersArray;
    }

    public String createUser(UserBean user) throws FirebaseAuthException {
        UserRecord.CreateRequest request = new UserRecord.CreateRequest()
                .setEmail(user.getEmail())
                .setPassword(user.getPassword());
        System.out.println(user.getEmail());
        UserRecord userRecord = FirebaseAuth.getInstance().createUser(request);

        return "Successfully created new user: " + userRecord.getUid();
    }

}
