package com.example.suitup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.model.Document;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class PostListActivity extends AppCompatActivity {

    private RecyclerView postRecyclerView;
    private RecyclerView.Adapter postAdapter;
    private ArrayList<Post> posts;
    private String TAG = "PostListActivity";
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_list);

        postRecyclerView = findViewById(R.id.postRecyclerView);
        postRecyclerView.setHasFixedSize(true);
        postRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAuth = FirebaseAuth.getInstance();
        final FirebaseFirestore db = FirebaseFirestore.getInstance();
        final FirebaseUser currentUser = mAuth.getCurrentUser();

        posts = new ArrayList<Post>();
        postAdapter = new PostAdapter(posts, this);
        postRecyclerView.setAdapter(postAdapter);
        db.collection("posts").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                if(error!=null){
                    Log.d(TAG, "listen:error", error);
                    return;
                }

                for(DocumentChange documentChange: value.getDocumentChanges()){

                    ArrayList<String>  temp = (ArrayList<String>) documentChange.getDocument().get("liked");
                    Post post = new Post(documentChange.getDocument().getId(), documentChange.getDocument().getString("username"),
                            temp, documentChange.getDocument().getString("price"),
                            documentChange.getDocument().getString("image_url"), documentChange.getDocument().getString("user_id"));
                    int index=0;
                    switch (documentChange.getType()){
                        case ADDED:
                            posts.add(post);

                            break;
                        case MODIFIED:
                            for(Post post_itr: posts){
                                if(post_itr.getDocumentId().equals(post.getDocumentId())){
                                    posts.set(index, post);
                                    break;
                                }
                                index++;
                            }
                            break;
                        case REMOVED:

                            for(Post post_itr: posts){
                                if(post_itr.getDocumentId().equals(post.getDocumentId())){
                                    posts.remove(index);
                                    break;
                                }
                                index++;
                            }
                            break;
                    }
                    postAdapter.notifyDataSetChanged();
                }
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
    }
}