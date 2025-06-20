import { useState } from "react";
import ObjectiveData from "../data/ObjectiveData";
import SwimMapping from "../mapping/SwimMapping";
import { useRouter } from "next/navigation";


const useObjectiveApi = () => {
    const route = useRouter();

    const [objectives, setObjectives] = useState<ObjectiveData[]>([]);

    const fetchObjectivesBySwimmerId = async ({ swimmerId }: { swimmerId: number }) => {
        try {
            const response = await fetch(`http://localhost:8080/api/objectives/swimmer/${swimmerId}`, {
                method: "GET",
                headers: {
                    Accept: "application/json",
                },
            });

            if(!response.ok) {
                throw new Error(`Erreur HTTP: ${response.status}`);
            }

            setObjectives(await response.json());
            return objectives;

        } catch (e) {
            console.error(e);
        }
    }

    const createObjective = async ({
    swim,
    swimmerId,
    distance,
    time,
}: {
    swim: number;
    swimmerId: number;
    distance: string;
    time: string;
}) => {
    const swimObj = SwimMapping.find((s) => s.id === swim);
    if (!swimObj) {
        console.error("Nage invalide");
        return;
    }

    const newObjective = {
        swimId: swimObj.id,
        swimmerId,
        distance: parseInt(distance, 10),
        time: parseInt(time, 10),
    };

    try {
        const response = await fetch("http://localhost:8080/api/objectives", {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
            },
            body: JSON.stringify(newObjective),
        });

        if (!response.ok) {
            const errorText = await response.text();
            console.error("Erreur serveur :", response.status, errorText);
            return;
        }

        console.log("L'objectif a bien été créé");
        route.push(`/objectives/swimmer/${swimmerId}`);
    } catch (error) {
        console.error("Erreur réseau :", error);
    }
};


    return {
        objectives,
        fetchObjectivesBySwimmerId,
        createObjective,
    }

}

export default useObjectiveApi;