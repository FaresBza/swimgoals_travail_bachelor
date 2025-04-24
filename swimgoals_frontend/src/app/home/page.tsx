"use client";

import { useEffect } from "react";

import AddButton from "../components/AddButton";

import './../styles/BackgroundImage.scss'
import './../styles/Home.scss'
import './../styles/Scroll.scss'
import './../styles/FormGroup.scss';

import useGroupApi from "../hooks/useGroupApi";
import { useRouter } from "next/navigation";
import useLocalStorage from "../hooks/useLocalStorage";

const Home = () => {
    const route = useRouter();

    const { groups, fetchGroupsByCoachId, fetchAllGroups } = useGroupApi();
    const { coachId, swimmerId, roleId, recoverUserId, recoverRoleId } = useLocalStorage();

    const goToSwimmersGroupPage = () => {
        route.push("/group");
    }

    useEffect(() => {
        if(roleId) { 
            recoverUserId();
        }
        recoverRoleId();
    }, []);

    useEffect(() => { 
        if (coachId) { fetchGroupsByCoachId({coachId}); } 
        if (swimmerId) { fetchAllGroups(); }
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
