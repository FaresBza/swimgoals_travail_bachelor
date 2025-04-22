"use client";

import { useEffect, useState } from "react";

import AddButton from "../components/AddButton";

import './../styles/BackgroundImage.scss'
import './../styles/Home.scss'
import './../styles/Scroll.scss'
import './../styles/FormGroup.scss';

import useGroupApi from "../hooks/useGroupApi";
import GroupData from "../data/GroupData";
import { useRouter } from "next/navigation";

const Home = () => {
    const [groups, setGroups] = useState<GroupData[]>([]);
    const [roleId, setRoleId] = useState<number>(0);
    const [coachId, setCoachId] = useState<number>(0);

    const route = useRouter();

    const { fetchGroupsByCoachId } = useGroupApi();

    const recoverCoachId = () => {
        const storedUser = localStorage.getItem("user");

        if (!storedUser) {
            console.error("Aucun utilisateur trouvé dans le localStorage");
            return;
        }

        const user = JSON.parse(storedUser);
        if (user && user.roleId === 2) {
            setCoachId(user.id);
        } else {
            console.error("Utilisateur non valide ou non connecté");
        }
    };

    const recoverRoleId = () => {
        const storedUser = localStorage.getItem("user");

        if (!storedUser) {
            console.error("Aucun utilisateur trouvé dans le localStorage");
            return;
        }

        const user = JSON.parse(storedUser);
        setRoleId(user.roleId);
    };

    const loadGroups = async () => {
        try {
            const data = await fetchGroupsByCoachId({ coachId });
            if (data && Array.isArray(data)) {
                setGroups(data);
            } else {
                setGroups([]);
            }
        } catch (error) {
            console.error("Erreur lors de la récupération des groupes :", error);
            setGroups([]);
        };
    };

    const goToSwimmersGroupPage = () => {
        route.push("/group");
    }

    useEffect(() => {
        if (coachId) {
            loadGroups();
        }
        recoverRoleId();
        recoverCoachId();
    }, [coachId]);

    return (
        <div>
            <div className="container blur">
                <div className="scrollable-container">
                    <h1 className="main-title">Groupes</h1>
                    <main className="list-groups">
                        {groups.map((group, index) => (
                            <div key={index} className="group-card">
                                <h2 className="group-title">{group.name}</h2>
                                { roleId === 2 ? 
                                    <button className="btn-group" onClick={goToSwimmersGroupPage}>Voir les nageurs</button>
                                    : 
                                    <button className="btn-group">Rejoindre</button> 
                                }
                            </div>
                        ))}
                    </main>
                </div>
            </div>

            <footer>
                {roleId === 2 && (
                    <AddButton
                        onClick={() => {
                            route.push("/add-group");
                        }}
                    />
                )}
            </footer>
        </div>
    );
};

export default Home;
