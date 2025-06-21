"use client";

import { useParams, useRouter } from "next/navigation";
import { useEffect, useState } from "react";
import useObjectiveApi from "@/app/hooks/useObjectiveApi";
import useLocalStorage from "@/app/hooks/useLocalStorage";
import SwimMapping from "@/app/mapping/SwimMapping";
import AddButton from "./../../../components/AddButton";

import "./../../../styles/BackgroundImage.scss";
import "./../../../styles/Scroll.scss";
import "./../../../styles/Card.scss";
import useUserApi from "@/app/hooks/useUserApi";

// Fonction utilitaire pour obtenir le nom de la nage à partir de l'id
const getSwimNameById = (swim: number | { id: number; name: string }) => {
    const swimId = typeof swim === "object" ? swim.id : swim;
    const found = SwimMapping.find((s) => s.id === swimId);
    return found ? found.name : "Inconnu";
};


const Objectives = () => {
    const { id } = useParams();
    const route = useRouter();

    const [firstName, setFirstName] = useState("");

    const { getSwimmerFirstNameById } = useUserApi();
    const { objectives, fetchObjectivesBySwimmerId } = useObjectiveApi();
    const { roleId, recoverRoleId } = useLocalStorage();

    const goToAddResultPage = (objectiveId: number) => {
        route.push(`/objectives/result/${objectiveId}`);
    }

    useEffect(() => {
        recoverRoleId();
        if (id && typeof id === "string") {
            fetchObjectivesBySwimmerId({ swimmerId: parseInt(id, 10) });
            getSwimmerFirstNameById(parseInt(id, 10)).then(setFirstName);
        }
    }, [id, roleId]);

    return (
        <div className="container blur">
            <div className="scrollable-container">
                <h1>{firstName}</h1>
                <main className="list-objectives">
                    {objectives.map((objective) => {
                        return (
                            <div key={objective.id} className="objective-card ">
                                <h2 className="objective-title">{objective.distance}m {getSwimNameById(objective.swim)}</h2>
                                <p className="objective-info">Temps : {objective.time} min</p>
                                {roleId === 2 && 
                                    <button 
                                        className="btn-group"
                                        onClick={() => goToAddResultPage(objective.id)}
                                    >
                                        Résultat
                                    </button>
                                }
                            </div>
                        );
                    })}
                </main>
            </div>
            <footer>
                {roleId === 2 && (
                    <AddButton
                        onClick={() => {
                            route.push(`/add-objective/${id}`);
                        }}
                    />
                )}
            </footer>
        </div>
    );
};

export default Objectives;
