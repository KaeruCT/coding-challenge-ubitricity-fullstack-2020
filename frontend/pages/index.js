import fetch from 'unfetch';
import useSWR, { trigger } from 'swr';
import ChargingPointsWidget from '../components/ChargingPointsWidget';
import Report from '../components/Report';
import Error from '../components/Error';
import Loading from '../components/Loading';

const fetcher = url => fetch(url).then(r => r.json());

const chargingPointsPath = `${process.env.apiBaseUrl}chargingPoints`;

export default function Index({ chargingPoints }) {
    const { data, error, revalidate } = useSWR(
        chargingPointsPath,
        { fetcher, refreshInterval: 5000 },
    );

    async function updateChargingPointStatus(id, occupied) {
        await fetch(`${chargingPointsPath}/${id}`, {
            method: 'PATCH',
            headers: { 'Content-Type': 'application/json' },                    
            body: JSON.stringify({ occupied }),
        });
        revalidate();
    }
    
    function connect(id) {
        updateChargingPointStatus(id, true);
    }
    
    function disconnect(id) {
        updateChargingPointStatus(id, false);
    }

    if (error) return <Error>failed to load :( {error}</Error>;
    if (!data) return <Loading />;

    return (
        <div className="index">
            <h1>
                Carpark Ubi
                <Report chargingPoints={data} />
            </h1>
            
            <ChargingPointsWidget
                connect={connect}
                disconnect={disconnect}
                chargingPoints={data}
            />

            <style jsx>{`
                .index {
                    width: 320px;
                    max-width: 100%;
                    margin: 0 auto;
                }
                h1 {
                    text-transform: uppercase;
                    padding: 15px;
                    font-size: 16px;
                    line-height: 32px;
                }
                @media (min-width: 768px) {
                    .index {
                        width: 760px;
                    }
                    h1 {
                        font-size: 32px;
                    }
                }
            `}</style>

            <style jsx global>{`
                html {
                    height: 100%;
                }
                body {
                    margin: 0;
                    padding: 0;
                    color: #eee;
                    height: 100%;
                    font-family: sans-serif;
                    background: #124;
                    background: linear-gradient(#124 0%, #2b3b59 100%) fixed;
                }
                * {
                    box-sizing: border-box;
                }
            `}</style>
        </div>
    );
}
