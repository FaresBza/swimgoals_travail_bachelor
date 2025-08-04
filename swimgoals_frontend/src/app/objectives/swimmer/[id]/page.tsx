"use client";

import { useParams, useRouter } from "next/navigation";
import { useEffect, useState } from "react";
import useObjectiveApi from "@/app/hooks/useObjectiveApi";
import useLocalStorage from "@/app/hooks/useLocalStorage";
import SwimMapping from "@/app/mapping/SwimMapping";
import AddButton from "./../../../components/AddButton";
import Image from "next/image";

import "./../../../styles/BackgroundImage.scss";
import "./../../../styles/Scroll.scss";
import "./../../../styles/Card.scss";
import './../../../styles/MobileNavbar.scss'


import useUserApi from "@/app/hooks/useUserApi";
import BackButton from "@/app/components/BackButton";

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

    const goToPerformancesPage = () => {
        route.push("/charts");
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
                            <div key={objective.id} className="objective-card ">
                                <h2 className="objective-title">{objective.distance}m {getSwimNameById(objective.swim)}</h2>
                                <p className="objective-info">Temps : {objective.time} min</p>
                                <div className="btn-group">
                                    {roleId === 2 && 
                                        <div>
                                            <button 
                                                className="btn"
                                                onClick={() => goToAddResultPage(objective.id)}
                                            >
                                                <Image
                                                    src="/icons/chrono.svg"
                                                    alt="Statistics Icon"
                                                    width={25}
                                                    height={25}
                                                />
                                            </button>
                                            <button 
                                                className="btn"
                                                onClick={() => goToOneObjectivePerformance(objective.id)}
                                            >
                                                <Image
                                                    src="/icons/statistics-white.svg"
                                                    alt="Statistics Icon"
                                                    width={25}
                                                    height={25}
                                                    onClick={goToPerformancesPage}
                                                />
                                            </button>
                                        </div>
                                    }
                                </div>
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
                {roleId === 3 && (
                    <nav className="swimmer-navbar">
                        <button className="btn objectives-button-nav">
                            <Image src="/icons/objectives-white.svg" alt="Objectives Icon" width={24} height={24} />
                        </button>
                        <button className="btn performances-button-nav">
                            <Image
                                src="/icons/statistics.svg"
                                alt="Statistics Icon"
                                width={24}
                                height={24}
                                onClick={goToPerformancesPage}
                            />
                        </button>
                    </nav>
                )}
            </footer>
        </div>
    );
};

export default Objectives;
