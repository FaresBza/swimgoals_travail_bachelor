"use client";

import { useParams, useRouter } from "next/navigation";
import { useEffect, useState } from "react";
import useObjectiveApi from "@/app/hooks/useObjectiveApi";
import useLocalStorage from "@/app/hooks/useLocalStorage";
import AddButton from "./../../../components/AddButton";

import "./../../../styles/BackgroundImage.scss";
import "./../../../styles/Scroll.scss";
import "./../../../styles/Card.scss";

import useUserApi from "@/app/hooks/useUserApi";
import BackButton from "@/app/components/BackButton";
import ObjectiveCard from "@/app/components/ObjectiveCard";

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

    const goToOneObjectivePerformance = (objectiveId: number) => {
        route.push(`/charts/objective/${objectiveId}`)
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
                { roleId === 2 &&  <BackButton />}
                { roleId === 2 &&  <h1>{firstName}</h1> }
                { roleId === 3 && <h1>Objectifs</h1> }
                <main className="list-objectives">
                    {objectives.map((objective) => {
                        return (
                            <div key={objective.id}>
                                <ObjectiveCard
                                    key={objective.id}
                                    objective={objective}
                                    roleId={roleId}
                                    goToAddResultPage={goToAddResultPage}
                                    goToOneObjectivePerformance={goToOneObjectivePerformance}
                                />
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
