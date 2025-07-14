"use client";

import { useEffect } from "react";
import { useParams, useRouter } from "next/navigation";
import useGroupApi from "@/app/hooks/useGroupApi";

import './../../styles/BackgroundImage.scss';
import './../../styles/Scroll.scss';
import './../../styles/Card.scss';


const GroupPage = () => {
    const { id } = useParams();
    const { swimmers, groupName, fetchGroupDetails } = useGroupApi();

    const route = useRouter();

    const goToSwimmerObjectivesPage = (swimmerId: number) => {
        route.push(`/objectives/swimmer/${swimmerId}`);
    };

    useEffect(() => {
        if (id) {
            fetchGroupDetails(id.toString());
        }
    }, [id]);

    return (
        <div className="container blur">
            <div className="scrollable-container">
                <h1>{groupName}</h1>
                <main className="list-swimmers">
                    {swimmers.map((user) => (
                        <div 
                            key={user.id} 
                            className="swimmer-card"
                            onClick={() => goToSwimmerObjectivesPage(user.id)}
                        >
                            <p className="swimmer-name">{user.firstname}</p>
                        </div>
                    ))}
                </main>
            </div>
        </div>
    );
};

export default GroupPage;
