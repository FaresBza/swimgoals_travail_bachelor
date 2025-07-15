"use client";

import { useEffect, useState } from "react";
import { useParams, useRouter } from "next/navigation";
import useGroupApi from "@/app/hooks/useGroupApi";

import './../../styles/BackgroundImage.scss';
import './../../styles/Scroll.scss';
import './../../styles/Card.scss';

const GroupPage = () => {
    const { id } = useParams();
    const { swimmers, fetchGroupDetails, getGroupNameById, error } = useGroupApi();
    const [groupName, setGroupName] = useState<string>("");

    const router = useRouter();

    const goToSwimmerObjectivesPage = (swimmerId: number) => {
        router.push(`/objectives/swimmer/${swimmerId}`);
    };

    const fetchData = async () => {
        if (id) {
            await fetchGroupDetails(id.toString());
            const name = await getGroupNameById({ id: Number(id) });
            setGroupName(name);
        }
    };

    useEffect(() => {
        fetchData();
    }, [id]);

    return (
        <div className="container blur">
            <div className="scrollable-container">
                <h1>{groupName}</h1>
                {error && <p style={{ color: "red" }}>{error}</p>}
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
