"use client"

import { useParams } from "next/navigation";

const ObjectiveResult = () => {
    const { objectiveId } = useParams();

    return (
        <div>
            <h1>Résultat de objectif {objectiveId}</h1>
        </div>
    );
};

export default ObjectiveResult;
