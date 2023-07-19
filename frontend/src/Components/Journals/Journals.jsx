import "./Journals.css";
import {useEffect, useState} from "react";
import {
    Box,
    Button,
    Card,
    CardActions,
    CardContent,
    CardMedia,
    Container,
    Grid,
    Stack,
    Typography
} from "@mui/material";

const Journals = () => {
    const [journalList, setJournalList] = useState([]);
    const [loading, setLoading] = useState(true);

    useEffect(() => {
        fetch("/api/journal/1/all")
            .then((res) => res.json())
            .then((result) => {
                console.log(result);
                setJournalList(result);
                setLoading(false);
            })
            .catch((error) =>
                console.log(`An error occurred at fetching from /api/journal:${error}`)
            );
    }, [])

    // if (loading) {
    //     return <Loading />;
    // }

    return (
        <>
            <Box
                sx={{
                    bgcolor: 'background.paper',
                    pt: 8,
                    pb: 6,
                }}
            >
                <Container maxWidth="sm">
                    <Typography
                        component="h1"
                        variant="h2"
                        align="center"
                        color="text.primary"
                        gutterBottom
                    >
                        Your Journals
                    </Typography>
                    <Typography variant="h5" align="center" color="text.secondary" paragraph>
                        Capture the essence of your travel experiences with our journals. Revisit cherished memories, add vibrant photos, and share your adventures with friends, all in one convenient space.
                    </Typography>
                </Container>
            </Box>
            <Container sx={{py: 8}} maxWidth="md">
                <Grid container spacing={4}>
                    {journalList.map((journal) => (
                        <Grid item key={journal.id} xs={12} sm={6} md={4}>
                            <Card
                                sx={{height: '100%', display: 'flex', flexDirection: 'column'}}
                            >
                                <CardMedia
                                    component="div"
                                    sx={{
                                        // 16:9
                                        pt: '56.25%',
                                    }}
                                    image="https://source.unsplash.com/random?wallpapers"
                                />
                                <CardContent sx={{flexGrow: 1}}>
                                    <Typography gutterBottom variant="h5" component="h2">
                                        {journal.title}
                                    </Typography>
                                    <Typography>
                                        {journal.created.split('T')[0]}
                                    </Typography>
                                </CardContent>
                                <CardActions>
                                    <Button size="small">Open journal</Button>
                                </CardActions>
                            </Card>
                        </Grid>
                    ))}
                </Grid>
            </Container>
        </>
    );

}

export default Journals;