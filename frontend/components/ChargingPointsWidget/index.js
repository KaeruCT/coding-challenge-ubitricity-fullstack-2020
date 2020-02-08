import ChargingPoint from './ChargingPoint';

function ChargingPointsWidget({ chargingPoints, connect, disconnect }) {
    return (
        <ul>
            {chargingPoints.map(chargingPoint => (
                <ChargingPoint
                    key={chargingPoint.id}
                    connect={connect}
                    disconnect={disconnect}
                    {...chargingPoint} />
            ))}
            <style jsx>{`
                ul {
                    display: flex;
                    align-items: center;
                    justify-content: space-around;
                    flex-wrap: wrap;
                    flex-direction: row;
                    padding: 0;
                    max-width: 100%;
                    width: 320px;
                    margin: 0 auto;
                }
                @media (min-width: 768px) {
                    ul {
                        width: 760px;
                    }
                }
            `}</style>
        </ul>
    );
}

export default ChargingPointsWidget;