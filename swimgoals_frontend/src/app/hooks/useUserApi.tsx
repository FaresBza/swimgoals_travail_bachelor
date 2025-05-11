// import { useState } from "react";
import { useRouter } from "next/navigation";
import UserAuthData from "../data/UserAuthData";
import RoleMapping from "../mapping/RoleMapping";
import { userRoleEnum } from "../enum/userRoleEnum";

const useUserApi = () => {

    const route = useRouter();

    const handleRegister = async ({ firstname, lastname, email, password, role }: UserAuthData): Promise<void> => {
        const roleId = RoleMapping[role];

        if (!roleId) {
            console.error("Erreur : rôle inconnu", role);
            return;
        }

        const newUser = { firstname, lastname, email, password, roleId }; 

        try {
            const response = await fetch("http://localhost:8080/api/register", {
                method: "POST",
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(newUser)
            });

            const data = await response.json().catch(() => null);
            const userId = data.id;

            localStorage.setItem("user", JSON.stringify({
            id: userId,
            firstname: newUser.firstname, 
            lastname: newUser.lastname, 
            roleId: newUser.roleId 
        }));

            if (response.ok) {
                if (newUser.roleId === userRoleEnum.admin) {
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

        const loginUserDetails = { email, password };

        try {
            const response = await fetch("http://localhost:8080/api/login", {
                method: "POST",
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify(loginUserDetails)
            });

            const text = await response.text();
            const data = JSON.parse(text);

            if (response.ok) {

                localStorage.setItem("user", JSON.stringify({
                    id: data.id,
                    firstname: data.firstname,
                    lastname: data.lastname,
                    roleId: data.role.id
                }));

                switch (data.role.id) {
                    case userRoleEnum.admin:
                        route.push("/home-admin");
                        break;
                    case userRoleEnum.coach:
                        route.push("/home");
                        break;
                    case userRoleEnum.swimmer:
                        route.push(`/objectives/swimmer/${data.id}`);
                        break;
                }
                
            } else {
                console.error("Erreur lors de la connexion", data.message);
            }
        } catch (error) {
            console.error("Erreur réseau :", error);
        }
    };

    return { 
        handleRegister,
        handleLogin 
    };
};

export default useUserApi;
