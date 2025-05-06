import { useRouter } from "next/navigation";
import GroupData from "../data/GroupData";
import { useState } from "react";
import { UserGroupData } from "../data/userGroupData";

const useGroupApi = () => {
    
    const [groups, setGroups] = useState<GroupData[]>([]); 
    const [swimmers, setSwimmers] = useState<UserGroupData[]>([]);
    const [groupName, setGroupName] = useState<string>("");
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

            setGroups(await response.json());
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

            setGroups(await response.json());    
            return groups;

        } catch (e) {
            console.error(e);
        }
    }

    const fetchGroupDetails = async (groupId: string) => {
        try {
            const resSwimmers = await fetch(`http://localhost:8080/api/groups/${groupId}`);
            const swimmersData = await resSwimmers.json();
            setSwimmers(swimmersData);

            const resGroup = await fetch(`http://localhost:8080/api/group/${groupId}`);
            const groupData = await resGroup.json();
            setGroupName(groupData.name);

        } catch (error) {
            console.error("Erreur lors de la récupération du groupe :", error);
        }
    };

    const createGroup = async ({ coachId, name}: {coachId: number, name: string}): Promise<void> => {
        
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
    }

    const joinGroup = async (groupId: number, swimmerId: number) => {
        try {
            const response = await fetch("http://localhost:8080/api/join-group", {
                method: "POST",
                headers: {
                    "Content-Type": "application/json",
                },
                body: JSON.stringify({
                    swimmerId: swimmerId,
                    groupId: groupId,
                }),
            });

            if (!response.ok) console.error("Erreur lors de l'ajout au groupe");
            return await response.json();
        } catch (error) {
            console.error("Erreur lors de la tentative de rejoindre un groupe :", error);
        }
    };

    return { 
        groups, 
        swimmers, 
        groupName, 
        fetchAllGroups, 
        fetchGroupsByCoachId, 
        fetchGroupDetails, 
        createGroup, 
        joinGroup 
    }
}

export default useGroupApi;