import "./JournalAlbum.css";
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
import {useNavigate} from "react-router-dom";

const JournalAlbum = () => {
    const [journalList, setJournalList] = useState([]);
    const [loading, setLoading] = useState(true);

    const navigate = useNavigate();

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

    const handleRedirection = (id) => {
        navigate(`/${id}`);
    }

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
                        component="h3"
                        variant="h3"
                        align="center"
                        color="text.primary"
                        gutterBottom
                    >
                        Your Journals
                    </Typography>
                    <Typography variant="h5" align="center" color="text.secondary" paragraph>
                        Capture the essence of your travel experiences with our journals. Revisit cherished memories,
                        add vibrant photos, and share your adventures with friends, all in one convenient space.
                    </Typography>
                    <Stack
                        sx={{pt: 4}}
                        direction="row"
                        spacing={2}
                        justifyContent="center"
                    >
                        <Button variant="contained">Create new Journal</Button>
                        {/*TODO after clicking the component for adding new will pop*/}
                    </Stack>
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
                                    image="https://images.pexels.com/photos/669986/pexels-photo-669986.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1"
                                />
                                <CardContent sx={{flexGrow: 1}}>
                                    <Typography gutterBottom variant="h5" component="h2" sx={{
                                        fontFamily: 'Satisfy'
                                    }}>
                                        {journal.title}
                                    </Typography>
                                    <Typography>
                                        {journal.created.split('T')[0]}
                                    </Typography>
                                </CardContent>
                                <CardActions>
                                    <Button size="small" onClick={() => handleRedirection(journal.id)}>Open
                                        journal</Button>
                                </CardActions>
                            </Card>
                        </Grid>
                    ))}
                </Grid>
            </Container>
        </>
    );

}

export default JournalAlbum;