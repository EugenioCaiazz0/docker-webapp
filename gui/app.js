async function callApi() {
    const res = await fetch("/api/counter");
    const data = await res.json();

    document.getElementById("result").innerText =
        JSON.stringify(data, null, 2);
}