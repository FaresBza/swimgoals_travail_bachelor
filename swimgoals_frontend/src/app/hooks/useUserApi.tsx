// import { useState } from "react";
import { useRouter } from "next/navigation";
import UserData from "../data/UserData";
import RoleMapping from "../mapping/RoleMapping";

const useUserApi = () => {
    // const [userData, setUserData] = useState({});

    const route = useRouter();

    const handleRegister = async ({ firstname, lastname, email, password, role }: UserData): Promise<void> => {
        const roleId = RoleMapping[role];

        if (!roleId) {
            console.error("Erreur : rôle inconnu", role);
            return;
        }

        const newUser = { firstname, lastname, email, password, roleId }; 

        localStorage.setItem("user", JSON.stringify({ 
            firstname: newUser.firstname, 
            lastname: newUser.lastname, 
            roleId: newUser.roleId 
        }));
        // setUserData(newUser);

        try {
            const response = await fetch("http://localhost:8080/api/register", {
                method: "POST",
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(newUser)
            });

            const data = await response.json().catch(() => null);

            if (response.ok) {
                if (newUser.roleId === 1) {
                    route.push("/home-admin");
                } else {
                    route.push("/home");
                }

                console.log("Création de compte REUSSI !");
            } else {
                console.error("Erreur lors de l'inscription", data);
            }
        } catch (error) {
            console.error("Erreur réseau :", error);
        }
    };

    const handleLogin = async ({ email, password }: { email: string, password: string }) => {
        try {
            const response = await fetch("http://localhost:8080/api/login", {
                method: "POST",
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify({ email, password })
            });

            const text = await response.text();
            const data = JSON.parse(text);

            console.log(data);

            if (response.ok) {
                if(data.role.id === 2){
                    route.push("/home");
                }
            } else {
                console.error("Erreur lors de la connexion", data.message);
            }
        } catch (error) {
            console.error("Erreur réseau :", error);
        }
    };

    return { handleRegister, handleLogin };
};

export default useUserApi;
