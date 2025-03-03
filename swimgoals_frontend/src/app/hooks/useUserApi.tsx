interface UserData {
    firstname: string;
    lastname: string;
    email: string;
    password: string;
}

const useUserApi = () => {
    const handleRegister = async ({ firstname, lastname, email, password }: UserData): Promise<void> => {
        const newUser = { firstname, lastname, email, password };

        try {
            const response = await fetch("http://localhost:8080/api/register", {
                method: "POST",
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(newUser)
            });

            if (response.ok) {
                console.log("Création de compte REUSSI !");
            } else {
                console.error("Erreur lors de l'inscription");
            }
        } catch (error) {
            console.error("Erreur réseau :", error);
        }
    };

    return { handleRegister };
};

export default useUserApi;
