package societino.com.societinoverification;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SetOptions;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Trena on 02-01-2018.
 */

public class ChairmanAdapter extends RecyclerView.Adapter<ChairmanAdapter.MyViewHolder> {
    List<DocumentSnapshot> sList;
    Context context;
    FirebaseStorage storage = FirebaseStorage.getInstance();
    StorageReference storageRef = storage.getReference();
    FirebaseFirestore db;
    private LinearLayout l1;

    @Override
    public ChairmanAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.listelement, parent, false);
        return new MyViewHolder(itemView);
    }

    public ChairmanAdapter(List<DocumentSnapshot> s, Context c) {
        sList = s;
        context = c;
        db = FirebaseFirestore.getInstance();
    }

    @Override
    public void onBindViewHolder(final ChairmanAdapter.MyViewHolder holder, final int position) {
        final DocumentSnapshot doc = sList.get(position);
        final String cid = doc.getId();
        String cn = doc.getString("name");
        holder.cname.setText(cn);
        final Hero hero = new Hero();
        final String sId = doc.getString("socId");
        final DocumentReference socDoc = db.collection("society").document(sId);
        socDoc.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document != null) {
                        String sn = document.getString("name");
                        holder.sname.setText(sn);
                        hero.setSocAdd(document.getString("address"));
                        hero.setSocCity(document.getString("city"));
                        hero.setSocEmail(document.getString("email"));
                        hero.setSocName(document.getString("name"));
                        hero.setSocPin(document.getString("pincode"));
                        hero.setSocSecret(document.getString("secret"));
                        hero.setSocState(document.getString("state"));
                        hero.setChEmail(doc.getString("email"));
                        hero.setChGen(doc.getString("gender"));
                        hero.setChName(doc.getString("name"));
                        hero.setChUrl(doc.getString("imageUrl"));
                        hero.setChNum(doc.getString("number"));
                        hero.setSocUsername(document.getString("username"));
                    } else {

                    }
                } else {
                }
            }
        });


        holder.accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Map<String, Object> data = new HashMap<>();
                data.put("isVerified", true);

                db.collection("chairman").document(cid)
                        .set(data, SetOptions.merge());


                data.put("isVerified", true);

                db.collection("society").document(sId)
                        .set(data, SetOptions.merge());

                sList.remove(position);
                notifyDataSetChanged();
               // Log.d()
                String s1=hero.getSocUsername().toString();
                Map<String, Object> dat = new HashMap<>();
                dat.put("socName",s1);

                db.collection("chats").document(s1)
                        .set(dat)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {

                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {

                            }
                        });



                final String body = "Dear User,\nYour society has been successfully registered.\nThe code for administrators is: " + hero.getSocSecret() + " \nYour society id is: " + hero.getSocUsername() + "\nThanking you,\nThe Team.";


                new Thread(new Runnable() {

                    @Override
                    public void run() {
                        try {
                            GMailSender sender = new GMailSender("societinoapp@gmail.com",
                                    "societino app");
                            sender.sendMail("Hello from Societino", body,
                                    "societinoapp@gmail.com", doc.getString("email"));
                        } catch (Exception e) {
                            Log.e("SendMail", e.getMessage(), e);
                        }
                    }

                }).start();
                new Thread(new Runnable() {

                    @Override
                    public void run() {
                        try {
                            GMailSender sender = new GMailSender("societinoapp@gmail.com",
                                    "societino app");
                            sender.sendMail("Hello from Societino", body,
                                    "societinoapp@gmail.com", doc.getString("email"));
                        } catch (Exception e) {
                            Log.e("SendMail", e.getMessage(), e);
                        }
                    }

                }).start();


            }
        });

        l1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i1 = new Intent(context, viewIn.class);
                i1.putExtra("hero", hero);
                context.startActivity(i1);
            }
        });


        holder.reject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db.collection("society").document(sId)
                        .delete()
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Log.d("On success deletion.", "DocumentSnapshot successfully deleted!");
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Log.w("On success failure.", "Error deleting document", e);
                            }
                        });
                db.collection("chairman").document(cid)
                        .delete()
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Log.d("On success deletion.", "DocumentSnapshot successfully deleted!");
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Log.w("On success failure.", "Error deleting document", e);
                            }
                        });

                StorageReference imageRef = storageRef.child("Chariman Address Proof/"+sId);
                imageRef.delete().addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        // File deleted successfully
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception exception) {
                        // Uh-oh, an error occurred!
                    }
                });


                sList.remove(position);
                notifyDataSetChanged();

                final String rej = "Dear User,\nYour society registeration was unsuccessful.\nRegards,\nThe Team.";

                new Thread(new Runnable() {

                    @Override
                    public void run() {
                        try {
                            GMailSender sender = new GMailSender("societinoapp@gmail.com",
                                    "societino app");
                            sender.sendMail("Hello from Societino", rej,
                                    "societinoapp@gmail.com", doc.getString("email"));
                        } catch (Exception e) {
                            Log.e("SendMail", e.getMessage(), e);
                        }
                    }

                }).start();


            }
        });
    }

    @Override
    public int getItemCount() {
        return sList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView cname, sname;
        public Button accept, reject;

        public MyViewHolder(View view) {
            super(view);
            accept = view.findViewById(R.id.btnAccept);
            reject = view.findViewById(R.id.btnReject);
            cname = view.findViewById(R.id.tvcname);
            sname = view.findViewById(R.id.tvsname);
            accept = view.findViewById(R.id.btnAccept);
            reject = view.findViewById(R.id.btnReject);
            l1 = view.findViewById(R.id.mainLayout);


        }

    }
}