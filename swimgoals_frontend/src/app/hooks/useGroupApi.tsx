import GroupData from "../data/GroupData";

const useGroupApi = () => {
    
    const fetchAllGroups = async () => {
        try {
            const response = await fetch("http://localhost:8080/api/groups", {
                method: "GET",
                headers: {
                    Accept: "application/json",
                },
            });

            if (!response.ok) {
                throw new Error(`Erreur HTTP: ${response.status}`);
            }

            return await response.json();
        } catch (e) {
            console.error(e);
            return null;
        }
    }

    const createGroup = async ({ coach: { id: coachId }, name}: GroupData): Promise<void> => {
        
        const newGroup = { coach: { id: coachId }, name };

        try {
            const response = await fetch("http://localhost:8080/api/groups", {
                method: "POST",
                headers: {
                    "Content-Type": "application/json"
                },
                body: JSON.stringify(newGroup)
            });

            if (response.ok) {
                console.log("Le groupe a été créé avec succès !");
            } else {
                console.error("Erreur lors de la création :", response.status, " ", response.statusText);
            }

        } catch (e) {
            console.error("Erreur de création du groupe :", e);
        }
    };

    return { fetchAllGroups, createGroup } 
}

export default useGroupApi;