package com.example.zomato.db.actions;

import com.example.zomato.db.objects.User;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DbActions {

    private DatabaseReference databaseReference;

    public DbActions(){
        databaseReference = FirebaseDatabase.getInstance().getReference();
    }


    private void writeNewUser(String userId, String firstName, String lastName, String email, int[] favRestaurants) {
        User user = new User(firstName,lastName,email,favRestaurants);
        databaseReference.child("users").child(userId).setValue(user);
}

    private void addFavouriteRestaurants(int[] favouriteRestaurants, String userId) {
        databaseReference.child("users").child(userId).child("favRestaurants").setValue(favouriteRestaurants);
    }
}
