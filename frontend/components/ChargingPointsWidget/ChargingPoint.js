import { Fragment } from 'react';
import BoltIcon from './BoltIcon';
import Button from '../Button';

function ChargingPoint({ id, amps, occupied, fastCharging, connect, disconnect }) {
    return (
        <li>
            <div className={`charging-point ${occupied && 'occupied'}`}>
                <div className="id">
                    CP{id}
                </div>
                <div className="icon">
                    <BoltIcon fill="#bfd" width={60} height={60} />
                </div>
                {occupied && (
                    <Fragment>
                        <div className="amps">{amps}A</div>
                        <div className="charging-type">{fastCharging ? 'Fast' : 'Slow'}</div>
                    </Fragment>
                )}
                {occupied ? (
                    <Button onClick={() => disconnect(id)}>Disconnect</Button>
                ) : (
                    <Button onClick={() => connect(id)}>Connect</Button>
                )}
            </div>

            <style jsx>{`
                li {
                    list-style-type: none;
                    padding: 10px;
                    flex: 1 50%;
                    width: 50%;
                    height: 200px;
                }
                .charging-point {
                    position: relative;
                    border: #8ca 2px solid;
                    border-radius: 5px;
                    background-color: rgba(0, 0, 0, 0.2);
                    display: flex;
                    flex-direction: column;
                    padding: 15px;
                    text-align: center;
                    height: 100%;
                    display: flex;
                    justify-content: space-between;
                    animation: none;
                }
                .charging-point.occupied {
                    animation: glow ease-in-out 2s infinite;
                }
                .id {
                    position: absolute;
                    font-size: 12px;
                    top: 15px;
                    right: 15px;
                }
                .charging-type {
                    font-weight: bold;
                    text-transform: uppercase;
                    color: #8ca;
                }
                .icon {
                    display: block;
                    margin: 0 auto;
                    opacity: ${occupied ? 1 : 0.2}
                }
                @media (min-width: 768px) {
                    li {
                        flex: 1 20%;
                        width: 20%;
                    }
                }
                @keyframes glow {
                    0% {
                        box-shadow: none;
                    }
                    50% {
                        box-shadow: rgba(187, 255, 221, 0.6) 0 0 15px;
                    }
                    100% {
                        box-shadow: none;
                    }
                 }
            `}</style>
        </li>
    );
}

export default ChargingPoint;