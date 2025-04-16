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

    const route = useRouter();

    const { fetchAllGroups } = useGroupApi();

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
            const data = await fetchAllGroups();
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

    useEffect(() => {
        loadGroups();
        recoverRoleId();
    }, []);

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
                                    <button className="btn-group">Voir les nageurs</button>
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
