interface UserData {
    firstname: string;
    lastname: string;
    email: string;
    password: string;
    role: number;
}

const useUserApi = () => {
    const handleRegister = async ({ firstname, lastname, email, password, role }: UserData): Promise<void> => {
    const roleMapping: Record<string, number> = {
        admin: 1,
        coach: 2,
        swimmer: 3
    };

    const roleId = roleMapping[role];

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

        if (response.ok) {
            console.log("Création de compte REUSSI !");
        } else {
            console.error("Erreur lors de l'inscription", data);
        }
    } catch (error) {
        console.error("Erreur réseau :", error);
    }
};



    return { handleRegister };
};

export default useUserApi;
