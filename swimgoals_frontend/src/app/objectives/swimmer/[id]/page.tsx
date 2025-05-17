"use client";

import { useParams, useRouter } from "next/navigation";
import { useEffect } from "react";
import useObjectiveApi from "@/app/hooks/useObjectiveApi";

import './../../../styles/BackgroundImage.scss';
import './../../../styles/Scroll.scss';
import './../../../styles/Card.scss';
import useLocalStorage from "@/app/hooks/useLocalStorage";
import AddButton from "./../../../components/AddButton";


const Objectives = () => {
    const { id } = useParams();
    const route = useRouter();

    const { objectives, fetchObjectivesBySwimmerId } = useObjectiveApi();
    const { roleId, recoverRoleId } = useLocalStorage();

    useEffect(() => {
        recoverRoleId();
        if (id && typeof id === "string") {
            fetchObjectivesBySwimmerId({ swimmerId: parseInt(id, 10) });
        }
    }, [id, roleId])

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
            <footer>
                {roleId === 2 && (
                    <AddButton
                        onClick={() => {
                            route.push("/add-objective");
                        }}
                    />
                )}
            </footer>
        </div>
    );
};

export default Objectives;
