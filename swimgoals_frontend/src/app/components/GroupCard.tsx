

interface GroupProps {
    name: string;
}

const GroupCard = ({ name }: GroupProps) => {

    return (
        <div className="group-card">
            <h2 className="group-title">{name}</h2>
        </div>
    )
}

export default GroupCard;