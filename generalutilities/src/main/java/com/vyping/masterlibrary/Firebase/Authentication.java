package com.vyping.masterlibrary.Firebase;

import static com.vyping.masterlibrary.Common.Definitions.AUTH_BLOCKED;
import static com.vyping.masterlibrary.Common.Definitions.AUTH_DELETED;
import static com.vyping.masterlibrary.Common.Definitions.AUTH_DISABLED;
import static com.vyping.masterlibrary.Common.Definitions.AUTH_INVALID;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.vyping.masterlibrary.Common.Definitions;

import java.util.concurrent.Executor;

public class Authentication {

    private static FirebaseAuth firebaseAuth;
    private Interface Interface;

    private static String email, password;
    private static int Counter;


    //----- Setup -----//

    public Authentication() {

        firebaseAuth = FirebaseAuth.getInstance();
    }


    //----- Methods -----//

    public void logUp(String Email, String Password, Interface Interface) {

        email = Email;
        password = Password;
        this.Interface = Interface;

        firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener((Executor) this, new OnCompleteListener<>() {

            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if (task.isSuccessful()) {

                    FirebaseUser user = firebaseAuth.getCurrentUser();

                    Interface.Successfully(user);
                }
            }

            private void DumyVoid() {
            }

        }).addOnFailureListener((Executor) this, new OnFailureListener() {

            @Override
            public void onFailure(@NonNull Exception e) {

                String errorMessage = "Imposible crear su cuenta: " + e;

                Interface.Failure(errorMessage);
            }

            private void DumyVoid() {}
        });
    }

    public void logIn(String Email, String Password, Interface Interface) {

        email = Email;
        password = Password;
        this.Interface = Interface;

        firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener((Executor) this, new OnCompleteListener<>() {

            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if (task.isSuccessful()) {

                    FirebaseUser user = firebaseAuth.getCurrentUser();

                    Interface.Successfully(user);
                }
            }

            private void DumyVoid() {
            }

        }).addOnFailureListener((Executor) this, new OnFailureListener() {

            @Override
            public void onFailure(@NonNull Exception error) {

                String Error = String.valueOf(error);

                setError(Error);
            }

            private void DumyVoid() {}
        });
    }

    public void logOut() {

        firebaseAuth.signOut();
    }


    //----- Aditional Tools -----//

    private void setError(@NonNull @Definitions.LoginResponse String error) {

        String errorMessage;

        if (error.contains(AUTH_INVALID)) {

            Counter = Counter + 1;

            if (Counter < 3) {

                errorMessage = "¡La contraseña ingresada es incorrecta!\n\nIntento " + Counter + " de 3\n\nCon tres intentos fallidos se restablecera la contraseña por correo.";

            } else {

                errorMessage = "Revisa tu correo electronico '" + email + "', por seguridad te enviamos un correo para generar una nueva contraseña.";

                firebaseAuth.sendPasswordResetEmail(email);
            }

        } else if (error.contains(AUTH_DISABLED)) {

            errorMessage = "Su usuario ha sido inhabilitado por el administrador.\nContacte al administrador o intente con otra cuenta";


        } else if (error.contains(AUTH_BLOCKED)) {

            errorMessage = "Su cuenta se ha bloqueado temporalmente por actividad inusual.\nContacte al administrador o intente mas tarde.";

        } else if (error.contains(AUTH_DELETED)) {

            errorMessage = "Su usuario ha sido borrado por el administrador.\nContacte al administrador o intente registrar su cuenta nuevamente.";

        } else {

            errorMessage = error;
        }

        Interface.Failure(errorMessage);
    }


    // ----- Interface ----- //

    public interface Interface {

        void Successfully(FirebaseUser user);
        void Failure(String errorMessage);
    }
}
