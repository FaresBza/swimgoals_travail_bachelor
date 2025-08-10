import Image from 'next/image';
import SwimMapping from '../mapping/SwimMapping';

interface Objective {
    id: number;
    distance: string;
    swim: number;
    time: string;
}

interface ObjectiveCardProps {
    objective: Objective;
    roleId: number;
    goToAddResultPage: (objectiveId: number) => void;
    goToOneObjectivePerformance: (objectiveId: number) => void;
}

const getSwimNameById = (swim: number | { id: number; name: string }) => {
    const swimId = typeof swim === "object" ? swim.id : swim;
    const found = SwimMapping.find((s) => s.id === swimId);
    return found ? found.name : "Inconnu";
};

function ObjectiveCard({ objective, roleId, goToAddResultPage, goToOneObjectivePerformance }: ObjectiveCardProps) {

    return (
        <div className="objective-card">
            <h2 className="objective-title">
                {objective.distance}m {getSwimNameById(objective.swim)}
            </h2>
            <p className="objective-info">Temps : {objective.time} min</p>
    
            <div className="btn-group">
                {roleId === 2 && (
                    <button
                        className="btn"
                        onClick={() => goToAddResultPage(objective.id)}
                        aria-label="Ajouter un résultat"
                    >
                    <Image
                        src="/icons/chrono.svg"
                        alt="Icone Chrono"
                        width={25}
                        height={25}
                    />
                    </button>
                )}
        
                {[2, 3].includes(roleId) && (
                    <button
                        className="btn"
                        onClick={() => goToOneObjectivePerformance(objective.id)}
                        aria-label="Voir performances"
                    >
                    <Image
                        src="/icons/statistics-white.svg"
                        alt="Icone Statistiques"
                        width={25}
                        height={25}
                    />
                    </button>
                )}
            </div>
        </div>
    );
}

export default ObjectiveCard;