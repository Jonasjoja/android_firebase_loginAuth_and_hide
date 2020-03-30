package com.example.firebaselogin.auth;

import android.app.Activity;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class FireBaseManager {
    //Requires email and pass, min 6 chars

    FirebaseAuth auth; //current user object

    public FireBaseManager() { //Constructor
        auth = FirebaseAuth.getInstance(); //Gets instance of firebase auth
        setUpAuthStateListener(); //listener executed
    }

    private void setUpAuthStateListener(){ //Will keep listening for login status
        auth.addIdTokenListener(new FirebaseAuth.IdTokenListener() {
            @Override
            public void onIdTokenChanged(@NonNull FirebaseAuth firebaseAuth) {
                if (firebaseAuth.getCurrentUser() == null){
                    System.out.println("Signed out from firebase");
                } else{
                    System.out.println("Is signed in");
                }
            }
        });
    }

    public void signIn(String email, String pwd, final Activity activity){ //Sign in method
        auth.signInWithEmailAndPassword(email,pwd)
                .addOnCompleteListener(activity, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            System.out.println("Login success." +
                                    task.getResult().getUser().getEmail());
                        } else {
                            System.out.println("FAILED" + task.getException());
                        }

                    }
                });
    }

    public void signUp(String email,String pwd, final Activity activity){
        auth.createUserWithEmailAndPassword(email,pwd)
                .addOnCompleteListener(activity, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            System.out.println("New user created: " +
                                    task.getResult().getUser().getEmail());
                        } else {
                            System.out.println("FAILED! User not created!" +
                                    task.getException());
                        }
                    }
                });
    }

    public void logOut(){
        System.out.println(auth.getCurrentUser().toString() + "Signed out!");
        auth.signOut();
    }
}
