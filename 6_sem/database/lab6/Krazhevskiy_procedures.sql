-- ups_CalculateMoviesRating
CREATE DEFINER=`root`@`%` PROCEDURE `usp_CalculateMovieRating`(
    IN movieTitle TEXT
)
BEGIN
    DECLARE totalRatings INT;
    DECLARE totalSumRating DECIMAL(10,2);
    DECLARE avgRating DECIMAL(10,2);

    SELECT COUNT(rating), IFNULL(SUM(rating), 0) INTO totalRatings, totalSumRating
    FROM movie
    JOIN ratings ON movie.movieId = ratings.movieId
    WHERE movie.title = movieTitle;

    IF totalRatings > 0 THEN
        SET avgRating = totalSumRating / totalRatings;
    ELSE
        SET avgRating = 0;
    END IF;

    SELECT movieId, title AS movieTitle, avgRating AS rating
    FROM movie
    WHERE title = movieTitle;
END

-- usp_SearchMovies
CREATE DEFINER=`root`@`%` PROCEDURE `usp_SearchMovies`(
    IN startYear INT,
    IN finishYear INT,
    IN titleTemplate TEXT,
    IN genresTemplate TEXT
)
BEGIN
    CREATE TEMPORARY TABLE IF NOT EXISTS temp_movies (
        movieId INT,
        title TEXT,
        releaseYear INT,
        genre TEXT
    );

    TRUNCATE TABLE temp_movies;

    INSERT INTO temp_movies (movieId, title, releaseYear, genre)
    SELECT movieId, title, releaseYear, genre
    FROM movie
    WHERE releaseYear BETWEEN startYear AND finishYear
    AND LOWER(title) LIKE LOWER(CONCAT('%', titleTemplate, '%'))
    AND (
        CASE
            WHEN genresTemplate LIKE '%|%'
                THEN (
                    SELECT COUNT(*)
                    FROM (
                        SELECT DISTINCT genre
                        FROM movie
                        WHERE movieId = movie.movieId
                        AND genre IN (SELECT SUBSTRING_INDEX(genresTemplate, '|', -1) AS genre)
                    ) AS temp
                ) = (SELECT COUNT(DISTINCT SUBSTRING_INDEX(genresTemplate, '|', -1)) FROM movie WHERE movieId = movie.movieId)

            ELSE (
                    SELECT COUNT(*)
                    FROM (
                        SELECT DISTINCT genre
                        FROM movie
                        WHERE movieId = movie.movieId
                        AND genre = genresTemplate
                    ) AS temp
                ) = 1
        END
    );

    SELECT *
    FROM temp_movies;

    DROP TABLE IF EXISTS temp_movies;
END
