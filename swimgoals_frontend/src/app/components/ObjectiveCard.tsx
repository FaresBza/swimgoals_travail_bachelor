import Image from 'next/image';
import { ObjectiveCardProps } from '../data/ObjectiveCardDataProps';
import { getSwimNameById } from '../utils/getSwimName';

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