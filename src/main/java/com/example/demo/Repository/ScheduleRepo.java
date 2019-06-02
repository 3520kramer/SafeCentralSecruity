package com.example.demo.Repository;

/*
Starter med at impotere den tilsvarende model klasse til repository
Derefter impotere vi autowired for at få kommunikation mellem de forskellige klasser
Herefter impotere vi både beanpropertyrowmapper og rowmapper til at håndtere vores resultsets fra vores database

Til sidst importere vi jdbctemplate og repository, som skaber forbindelsen til vores database og laver klassen til
et brugbart repository

Vi indhenter også lige DateFormat, SimpleDateFormat, Date og List
 */

import com.example.demo.Model.Schedule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

//Starter med at deklarer det som værende et repository og implementere derefter vores interface

@Repository
public class ScheduleRepo implements RepoInterface{

    //Opretter et jdbctemplate objekt og autowire det

    @Autowired
    JdbcTemplate template;

    //Funktion til at hente fra 4 tables, med inner joins. Vi bruger en concat til et enkelt navn, som vi bruger til at vise medarbejderens fulde navn i en celle
    // til den rigtige kunde, derefter, bruger vi rowmapper til at håndtere resultset

    public List<Schedule> getAllSchedules(){
        String sql = "SELECT vagtplan_id, CONCAT(fornavn, ' ', efternavn) AS navn, starttid, sluttid, " +
                "timetal, dato, firma_navn, k.adresse, bydel, k.postnummer,\n" +
                "medarbejder_id, kunde_id FROM vagtplan v\n" +
                "JOIN medarbejdere m ON v.medarbejder_id_fk = m.medarbejder_id\n" +
                "JOIN kunder k ON v.kunder_id_fk = k.kunde_id\n" +
                "JOIN byer b ON k.postnummer = b.postnummer\n" +
                "GROUP BY vagtplan_id\n" +
                "ORDER BY dato;";
        RowMapper<Schedule> rowMapper = new BeanPropertyRowMapper<>(Schedule.class);
        return template.query(sql, rowMapper);
    }

    /*
    Starter med at oprette et dateformat, som matcher vores database, derefter laver vi et date objekt, og til sidst laver vi det date objekt om til en string
    Vi tager så den string over bruger den i vores select script, vi laver et date objekt udelukkende sådan så vi får fat i dagens dato. Man kan snyde dette, ved
    at ændre computerens dato osv. hvis man ønsker det
     */
    public List<Schedule> getTodaysSchedule(){
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date dato = new Date();
        String date = dateFormat.format(dato);

        String sql = "SELECT vagtplan_id, CONCAT(fornavn, ' ', efternavn) AS navn, fornavn, efternavn, starttid, sluttid, timetal, dato, firma_navn, k.adresse, bydel, k.postnummer,\n" +
                "medarbejder_id, kunde_id FROM vagtplan v\n" +
                "JOIN medarbejdere m ON v.medarbejder_id_fk = m.medarbejder_id\n" +
                "JOIN kunder k ON v.kunder_id_fk = k.kunde_id\n" +
                "JOIN byer b ON k.postnummer = b.postnummer\n" +
                "WHERE dato = ?\n" +
                "GROUP BY vagtplan_id\n" +
                "ORDER BY starttid;";
        RowMapper<Schedule> rowMapper = new BeanPropertyRowMapper<>(Schedule.class);
        return template.query(sql, rowMapper, date);
    }

    /*
    Ligner forhenværende metode, men denne her opretter ikke et date objekt, men modtager en string i stedet, som den bruger til at bruge dens select statement, sådan man selv
    kan indtaste hvilken dag man ønsker at se
     */
    public List<Schedule> getOneSchedule(String date){
        String sql = "SELECT vagtplan_id, CONCAT(fornavn, ' ', efternavn) AS navn, fornavn, efternavn, starttid, sluttid, timetal, dato, firma_navn, k.adresse, bydel, k.postnummer,\n" +
                "medarbejder_id, kunde_id FROM vagtplan v\n" +
                "JOIN medarbejdere m ON v.medarbejder_id_fk = m.medarbejder_id\n" +
                "JOIN kunder k ON v.kunder_id_fk = k.kunde_id\n" +
                "JOIN byer b ON k.postnummer = b.postnummer\n" +
                "WHERE dato = ?\n" +
                "GROUP BY vagtplan_id\n" +
                "ORDER BY starttid;";
        RowMapper<Schedule> rowMapper = new BeanPropertyRowMapper<>(Schedule.class);
        return template.query(sql, rowMapper, date);
    }

    /*
    Ligner forhenværende metode, men denne her metode modtager 2 strings, for at vælge en periode mellem 2 datoer
     */
    public List<Schedule> getScheduleDateFromTo(String date, String dateTo){
        String sql = "SELECT vagtplan_id, CONCAT(fornavn, ' ', efternavn) AS navn, fornavn, efternavn, starttid, sluttid, timetal, dato, firma_navn, k.adresse, bydel, k.postnummer,\n" +
                "medarbejder_id, kunde_id FROM vagtplan v\n" +
                "JOIN medarbejdere m ON v.medarbejder_id_fk = m.medarbejder_id\n" +
                "JOIN kunder k ON v.kunder_id_fk = k.kunde_id\n" +
                "JOIN byer b ON k.postnummer = b.postnummer\n" +
                "WHERE dato >= ? AND dato <= ?\n" +
                "GROUP BY vagtplan_id\n" +
                "ORDER BY dato;";
        RowMapper<Schedule> rowMapper = new BeanPropertyRowMapper<>(Schedule.class);
        return template.query(sql, rowMapper, date, dateTo);
    }

    //Simpel insert mysql, hvor vi bare modtager fra brugeren hvad der skal indsættes i databasens tabel vagtplan
    public Schedule createSchedule(Schedule s){
        String sql = "INSERT INTO vagtplan VALUES(null, ?, ?, ?, ?, ?, ?)";
        template.update(sql, s.getStarttid(), s.getSluttid(), s.getTimetal(), s.getDato(),
                s.getMedarbejder_id(), s.getKunde_id());
        return null;
    }

    //Metode vi bruger til at finde og videreføre de forskellige vagtplan id´er
    public Schedule findScheduleById(int schedule_id){
        String sql = "SELECT vagtplan_id, CONCAT(fornavn, ' ', efternavn) AS navn, fornavn, efternavn, starttid, sluttid, timetal, dato, firma_navn, k.adresse, bydel, k.postnummer,\n" +
        "medarbejder_id, kunde_id FROM vagtplan v\n" +
                "JOIN medarbejdere m ON v.medarbejder_id_fk = m.medarbejder_id\n" +
                "JOIN kunder k ON v.kunder_id_fk = k.kunde_id\n" +
                "JOIN byer b ON k.postnummer = b.postnummer\n" +
                "WHERE vagtplan_id = ?\n" +
                "GROUP BY vagtplan_id\n" +
                "ORDER BY starttid;";
        RowMapper<Schedule> rowMapper = new BeanPropertyRowMapper<>(Schedule.class);
        Schedule s = template.queryForObject(sql, rowMapper, schedule_id);
        return s;
    }

    //Metode til at faktisk at opdatere vores vagtplan, som bruger førhenværende metode
    public Schedule updateSchedule(Schedule schedule){
        String sql = "UPDATE vagtplan SET starttid = ?, sluttid = ?, timetal = ?, dato = ?, medarbejder_id_fk = ?, kunder_id_fk = ? WHERE vagtplan_id = ?";
        template.update(sql, schedule.getStarttid(), schedule.getSluttid(), schedule.getTimetal(), schedule.getDato(),
                schedule.getMedarbejder_id(), schedule.getKunde_id(), schedule.getVagtplan_id());
        return null;
    }

    //Metode til at slette vores vagtplan
    public Boolean delete(int id) {
        String sql = "DELETE from vagtplan WHERE vagtplan_id = ?";
        return template.update(sql, id) > 0;
    }


    /*
    For at få starttimen og sluttimen gemt i hver sin double har vi startet med at lave 2 strings, en for starttidspunktet og en for sluttidspunktet.
    Vi bruger så substring, til at kun at få de 2 første tegn fra både starttidspunktet og sluttidspunktet, herefter parser vi det til doubles.
    Nu har vi starttimen og sluttimen gemt i hver sin double.

    For at få startminut og slutminut gemt i hver sin double, gentager vi samme proces, dog starter vores substring fra 3. tegn
    og slutter på 5. tegn (eksclusiv 5. tegn), for så at gøre dette til timer, dividerer vi selvfølgelig med 60.

    For at sikre os, at der ikke kommer minus antal timer, laver vi et if-statement som sætter timetal til sluttiden i minutter,
    hvis starttime og sluttime er det samme, men slutminut er højere end startminut. Hvis dette ikke er sandt, fortsætter vi til et if-else-statement,
    som lægger 24 timer til, hvis sluttidspunktet er mindre end starttidspunkt, eller er det samme som starttidspunktet.
    Derefter trækker vi starttimen fra sluttimen, og herefter lægger vi summen af slutminut og startminut til, for at få timetallet.
    */

    public double getHoursWorked(String startTidString, String slutTidString) {
        double timetal;
        String startTidTimeString = startTidString.substring(0,2);
        String slutTidTimeString = slutTidString.substring(0,2);

        double startTidTime = Double.parseDouble(startTidTimeString);
        double slutTidTime = Double.parseDouble(slutTidTimeString);

        String startTidMinutString = startTidString.substring(3,5);
        String slutTidMinutString = slutTidString.substring(3,5);

        double startTidMinut = Double.parseDouble(startTidMinutString);
        double slutTidMinut = Double.parseDouble(slutTidMinutString);

        startTidMinut /= 60;
        slutTidMinut /= 60;

        if(slutTidTime == startTidTime && slutTidMinut > startTidMinut){
            timetal = slutTidMinut;

        }else if(slutTidTime <= startTidTime) {
            slutTidTime += 24;
            timetal = slutTidTime - startTidTime + (slutTidMinut - startTidMinut);

        }else {
            timetal = slutTidTime - startTidTime + (slutTidMinut - startTidMinut);
        }
        return timetal;
    }
}
