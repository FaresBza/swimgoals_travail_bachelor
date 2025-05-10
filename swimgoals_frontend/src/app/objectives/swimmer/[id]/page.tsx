"use client";

import useObjectiveApi from "@/app/hooks/useObjectiveApi";
import { useParams } from "next/navigation";
import { useEffect } from "react";

const Objectives = () => {
    const { id } = useParams();

    const { objectives, fetchObjectivesBySwimmerId } = useObjectiveApi();

    useEffect(() => {
        if (id && typeof id === "string") {
            fetchObjectivesBySwimmerId({ swimmerId: parseInt(id, 10) });
        }
    }, [id])

    return (
        <div>
            {objectives.map((objective) => (
                <div
                    key={objective.id}
                >
                    <h2>{objective.swimId}</h2>
                    <p>{objective.distance}</p>
                    <p>{objective.time}</p>
                </div>
            ))}
        </div>
    );
};

export default Objectives;
