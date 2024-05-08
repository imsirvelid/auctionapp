import React, {useState, useEffect} from "react";
import {useSubscription} from "react-stomp-hooks";


const ChildComponent = () => {
    const [message, setMessage] = useState("");
    useSubscription('/user/velid.imsir@gmail.com/queue', (message) => { console.log("DOSLO JE DO OVOG SRANJA"); setMessage(message.body)});
    useSubscription('/user/queue-velid.imsir@gmail.com', (message) => { console.log("DOSLO JE DO OVOG SRANJA"); setMessage(message.body)});
    useSubscription('/user/queue/velid.imsir@gmail.com', (message) => { console.log("DOSLO JE DO OVOG SRANJA"); setMessage(message.body)});

    return (
      <div> The message from websocket broker for myself is {message}</div>
    )
}

export default ChildComponent;