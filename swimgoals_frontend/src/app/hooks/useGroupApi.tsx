import { useRouter } from "next/navigation";
import GroupData from "../data/GroupData";

const useGroupApi = () => {

    const route = useRouter();
    
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

    const fetchGroupsByCoachId = async ({ coachId }: {coachId: number}) => {
        try {
            const response = await fetch(`http://localhost:8080/api/groups/coach/${coachId}`, {
                method: "GET",
                headers: {
                        Accept: "application/json",
                    },
            });

            if(!response.ok) {
                throw new Error(`Erreur HTTP: ${response.status}`);
            }
                
            return await response.json();

        } catch (e) {
            console.error(e);
        }
    }

    const createGroup = async ({ coachId, name}: GroupData): Promise<void> => {
        
        const newGroup = { coachId, name };

        try {
            const response = await fetch("http://localhost:8080/api/groups", {
                method: "POST",
                headers: {
                    "Content-Type": "application/json"
                },
                body: JSON.stringify(newGroup)
            });

            const data = await response.json().catch(() => null);
            const groupId = data.id;

            localStorage.setItem("group", JSON.stringify({
            id: groupId,
            name: newGroup.name, 
            coachId: coachId
        }));

            if (response.ok) {
                console.log("Le groupe a été créé avec succès !");
                route.push("home");
            } else {
                console.error("Erreur lors de la création :", response.status, " ", response.statusText);
            }

        } catch (e) {
            console.error("Erreur de création du groupe :", e);
        }
    };

    return { fetchAllGroups, fetchGroupsByCoachId, createGroup } 
}

export default useGroupApi;