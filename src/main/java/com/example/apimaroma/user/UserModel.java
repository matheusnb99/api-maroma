package com.example.apimaroma.user;

import com.example.apimaroma.CrudRepository;
import com.example.apimaroma.ratings.RatingBean;
import com.google.api.core.ApiFuture;
import com.google.cloud.Timestamp;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import com.google.gson.Gson;

import java.util.*;
import java.util.concurrent.ExecutionException;

public class UserModel implements CrudRepository<UserBean, String> {

    private Firestore dbFirestore = FirestoreClient.getFirestore();
    private CollectionReference usersTable = dbFirestore.collection("users");


    @Override
    public <S extends UserBean> S save(S entity) throws ExecutionException, InterruptedException {
        return null;
    }

    public <S extends UserBean> S saveUser(HashMap <String, Object> userMap) throws ExecutionException, InterruptedException {
        System.out.println(userMap.values());
        DocumentReference userRef = usersTable.document((String) userMap.get("id"));

        userMap.put("birthDate", new Date((Long) userMap.get("birthDateLong")));
        userMap.put("basket", new ArrayList<>());
        userMap.put("defaultAddress", null);

        userMap.remove("birthDateLong");

        userRef.set(userMap);

        Gson gson = new Gson();
        String json = gson.toJson(userMap);

        return (S) gson.fromJson(json, UserBean.class);
    }

    @Override
    public Optional<UserBean> findById(String primaryKey) throws ExecutionException, InterruptedException {
        DocumentReference documentReference = usersTable.document(primaryKey);
        ApiFuture<DocumentSnapshot> future = documentReference.get();
        DocumentSnapshot document = future.get();
        return Optional.ofNullable(document.toObject(UserBean.class));
    }


    public Iterable<UserBean> findByName(String name) throws ExecutionException, InterruptedException {
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
            if (!usersArray.contains(tempUser)) {
                usersArray.add(tempUser);
            }
        }
        return usersArray;
    }

    @Override
    public Iterable<UserBean> findAll() throws ExecutionException, InterruptedException {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public Timestamp delete(UserBean entity) throws ExecutionException, InterruptedException {
        return null;
    }

    @Override
    public boolean existsById(String primaryKey) {
        return false;
    }
}
