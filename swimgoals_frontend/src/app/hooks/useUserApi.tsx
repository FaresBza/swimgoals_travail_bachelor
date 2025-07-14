import { useState } from "react";
import { useRouter } from "next/navigation";
import UserAuthData from "../data/UserAuthData";
import RoleMapping from "../mapping/RoleMapping";
import { userRoleEnum } from "../enum/userRoleEnum";

const useUserApi = () => {
    const route = useRouter();
    const [error, setError] = useState<string | null>(null);

    const handleRegister = async ({ firstname, lastname, email, password, role }: UserAuthData): Promise<boolean> => {
        setError(null); 

        if (!firstname || !lastname || !email || !password || !role) {
            setError("Tous les champs sont obligatoires.");
            return false;
        }

        const roleId = RoleMapping[role];
        if (!roleId) {
            setError("Rôle utilisateur invalide.");
            return false;
        }

        const newUser = { firstname, lastname, email, password, roleId };

        try {
            const response = await fetch("http://localhost:8080/api/register", {
                method: "POST",
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(newUser),
            });

            const data = await response.json().catch(() => null);

            if (!response.ok) {
                setError(data?.message || "Erreur lors de l'inscription.");
                return false;
            }

            const userId = data?.id;

            localStorage.setItem("user", JSON.stringify({
                id: userId,
                firstname: newUser.firstname,
                lastname: newUser.lastname,
                roleId: newUser.roleId,
            }));

            if (newUser.roleId === userRoleEnum.admin) {
                route.push("/home-admin");
            } else {
                route.push("/home");
            }

            return true;

        } catch (error) {
            console.error("Erreur réseau :", error);
            setError("Erreur de connexion au serveur.");
            return false;
        }
    };

    const handleLogin = async ({ email, password }: { email: string; password: string }): Promise<boolean> => {
        setError(null);

        if (!email || !password) {
            setError("Email et mot de passe sont requis.");
            return false;
        }

        try {
            const response = await fetch("http://localhost:8080/api/login", {
                method: "POST",
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify({ email, password }),
            });

            const text = await response.text();
            const data = JSON.parse(text);

            if (!response.ok) {
                setError(data?.message || "Identifiants incorrects.");
                return false;
            }

            localStorage.setItem("user", JSON.stringify({
                id: data.id,
                firstname: data.firstname,
                lastname: data.lastname,
                roleId: data.role.id,
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

            return true;

        } catch (error) {
            console.error("Erreur réseau :", error);
            setError("Erreur de connexion au serveur.");
            return false;
        }
    };

    const getSwimmerFirstNameById = async (id: number): Promise<string> => {
        try {
            const response = await fetch(`http://localhost:8080/api/swimmer/${id}`, {
                method: "GET",
                headers: {
                    Accept: "application/json",
                },
            });

            if (!response.ok) {
                console.error("Erreur lors de la récupération du nageur :", response.status);
                return "Inconnu";
            }

            const data = await response.json();
            return data.firstname;

        } catch (error) {
            console.error("Erreur réseau :", error);
            return "Inconnu";
        }
    };

    return {
        handleRegister,
        handleLogin,
        getSwimmerFirstNameById,
        error
    };
};

export default useUserApi;
