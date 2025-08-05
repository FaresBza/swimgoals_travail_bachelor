"use client"

import { useEffect } from "react";
import useLocalStorage from "../hooks/useLocalStorage";
import useObjectiveApi from "../hooks/useObjectiveApi";

const ChartSwimmer = () => {

    const { swimmerId, roleId, recoverUserId, recoverRoleId } = useLocalStorage();
    const { objectives, fetchObjectivesBySwimmerId } = useObjectiveApi();

    useEffect(() => {
        recoverRoleId()
        if(roleId) { recoverUserId() }
        if(swimmerId) { fetchObjectivesBySwimmerId({ swimmerId }) }
        
    })

    return (
        <>
            <h1>{swimmerId}</h1>
            {objectives.map(o => (
                <h3 key={o.id}>{o.distance}m - {o.time}min</h3>
            ))}
        </>
    )
}

export default ChartSwimmer;