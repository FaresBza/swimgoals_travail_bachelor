"use client";

import { useEffect } from "react";
import { useParams } from "next/navigation";
import useGroupApi from "@/app/hooks/useGroupApi";

const GroupPage = () => {
    const { id } = useParams();
    const { swimmers, groupName, fetchGroupDetails } = useGroupApi();

    useEffect(() => {
        if (id) {
            fetchGroupDetails(id.toString());
        }
    }, [id]);

    return (
        <div>
            <h1>Groupe : {groupName}</h1>
            <h2>Liste des nageurs</h2>
            <ul>
                {swimmers.map((user) => (
                    <li key={user.id}>{user.firstname} {user.lastname}</li>
                ))}
            </ul>
        </div>
    );
};

export default GroupPage;
