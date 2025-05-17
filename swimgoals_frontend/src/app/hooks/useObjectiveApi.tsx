import { useState } from "react";
import ObjectiveData from "../data/ObjectiveData";


const useObjectiveApi = () => {

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

    

    return {
        objectives,
        fetchObjectivesBySwimmerId,
    }

}

export default useObjectiveApi;