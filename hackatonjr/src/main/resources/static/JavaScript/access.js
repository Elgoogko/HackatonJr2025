async function access(page){
    try{
        const response = await fetch('/' + page,{
            method: 'GET'
        });

        if (!response.ok) {
            throw new Error(`HTTP ${response.status}`);
        }

    } catch (e) {
        console.error(e);
    }
}