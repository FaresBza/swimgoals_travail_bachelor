"use client";

import { useParams } from "next/navigation";
import { useEffect } from "react";
import useObjectiveApi from "@/app/hooks/useObjectiveApi";

import './../../../styles/BackgroundImage.scss';
import './../../../styles/Scroll.scss';
import './../../../styles/Card.scss';


const Objectives = () => {
    const { id } = useParams();

    const { objectives, fetchObjectivesBySwimmerId } = useObjectiveApi();

    useEffect(() => {
        if (id && typeof id === "string") {
            fetchObjectivesBySwimmerId({ swimmerId: parseInt(id, 10) });
        }
    }, [id])

    return (
        <div className="container blur">
            <div className="scrollable-container">
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
        </div>
    );
};

export default Objectives;
