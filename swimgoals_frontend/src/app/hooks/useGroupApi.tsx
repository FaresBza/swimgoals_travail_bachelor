import { useRouter } from "next/navigation";
import GroupData from "../data/GroupData";
import { useState } from "react";
import { UserGroupData } from "../data/UserGroupData";

const useGroupApi = () => {
    const [groups, setGroups] = useState<GroupData[]>([]);
    const [swimmers, setSwimmers] = useState<UserGroupData[]>([]);
    const [error, setError] = useState<string | null>(null);

    const route = useRouter();

    const fetchAllGroups = async () => {
        try {
            const response = await fetch("http://localhost:8080/api/groups", {
                method: "GET",
                headers: {
                    Accept: "application/json",
                },
            });

            const data = await response.json();

            if (!response.ok) {
                setError(data?.message || "Erreur lors de la récupération des groupes.");
            }


            setGroups(data);
            return data;

        } catch (err) {
            setError(`Erreur de connexion au serveur : ${err}`);
        }
    };

    const fetchGroupsByCoachId = async ({ coachId }: { coachId: number }) => {
        try {
            const response = await fetch(`http://localhost:8080/api/groups/coach/${coachId}`, {
                method: "GET",
                headers: {
                    Accept: "application/json",
                },
            });

            const data = await response.json();

            if (!response.ok) {
                setError(data?.message || "Erreur lors de la récupération des groupes du coach ");
            }

            setGroups(data);
            return data;

        } catch (err) {
            setError(`Erreur de connexion au serveur : ${err}`);
        }
    };

    const fetchGroupDetails = async (groupId: string) => {
        try {
            const resSwimmers = await fetch(`http://localhost:8080/api/groups/${groupId}`, {
                method: "GET",
                headers: {
                    Accept: "application/json",
                },
            });

            const swimmersData = await resSwimmers.json();

            if(!resSwimmers.ok) {
                setError(swimmersData?.message || "Erreur lors de la récupération des swimmers");
            }

            setSwimmers(swimmersData);

        } catch (err) {
            setError(`Erreur de connexion au serveur : ${err}`);
        }
    };

    const getGroupNameById = async ({ id }: { id: number }) => {
        try {
            const response = await fetch(`http://localhost:8080/api/group/${id}`, {
                method: "GET",
                headers: {
                    Accept: "application/json",
                },
            });

            const data = await response.json();

            if (!response.ok) {
                setError(data?.message || "Erreur lors de la récupération des informations du groupe");
            }

            return data.name;

        } catch (err) {
            setError(`Erreur de connexion au serveur : ${err}`);
        }
    };

    const createGroup = async ({ coachId, name }: { coachId: number; name: string }): Promise<void> => {
        if (!name) {
            setError("Le champs nom est obligatoire");
        } else {
            const newGroup = { coachId, name };

            try {
                const response = await fetch("http://localhost:8080/api/groups", {
                    method: "POST",
                    headers: {
                        "Content-Type": "application/json",
                    },
                    body: JSON.stringify(newGroup),
                });

                const data = await response.json().catch(() => null);
                const groupId = data?.id;

                localStorage.setItem("group", JSON.stringify({
                    id: groupId,
                    name: newGroup.name,
                    coachId: coachId,
                }));

                if (response.ok) {
                    route.push("home");
                } else {
                    setError(data?.message || "Erreur lors de la création d'un groupe")
                }

            } catch (err) {
                setError(`Erreur de connexion au serveur : ${err}`);
            }
        }
        
    };

    const joinGroup = async (groupId: number, swimmerId: number) => {
        try {
            const response = await fetch("http://localhost:8080/api/join-group", {
                method: "POST",
                headers: {
                    "Content-Type": "application/json",
                },
                body: JSON.stringify({
                    swimmerId,
                    groupId,
                }),
            });

            if (!response.ok) {
                setError("Erreur lors de la tentative de rejoindre un groupe");
            }

            return await response.json();

        } catch (err) {
            setError(`Erreur de connexion au serveur : ${err}`);
        }
    };

    return {
        groups,
        swimmers,
        fetchAllGroups,
        fetchGroupsByCoachId,
        fetchGroupDetails,
        getGroupNameById,
        createGroup,
        joinGroup,
        error
    };
};

export default useGroupApi;
