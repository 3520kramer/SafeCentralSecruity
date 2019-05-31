package com.example.demo.Repository;

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

@Repository
public class ScheduleRepo implements RepoInterface{

    @Autowired
    JdbcTemplate template;

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

    public List<Schedule> getTodaysSchedule(){
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date dateObj = new Date();
        String date = dateFormat.format(dateObj);

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

    public Schedule createSchedule(Schedule s){
        String sql = "INSERT INTO vagtplan VALUES(null, ?, ?, ?, ?, ?, ?)";
        template.update(sql, s.getStarttid(), s.getSluttid(), s.getTimetal(), s.getDato(),
                s.getMedarbejder_id(), s.getKunde_id());
        return null;
    }

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

    public Schedule updateSchedule(Schedule schedule){
        String sql = "UPDATE vagtplan SET starttid = ?, sluttid = ?, timetal = ?, dato = ?, medarbejder_id_fk = ?, kunder_id_fk = ? WHERE vagtplan_id = ?";
        template.update(sql, schedule.getStarttid(), schedule.getSluttid(), schedule.getTimetal(), schedule.getDato(),
                schedule.getMedarbejder_id(), schedule.getKunde_id(), schedule.getVagtplan_id());
        return null;
    }

    public Boolean delete(int id) {
        String sql = "DELETE from vagtplan WHERE vagtplan_id = ?";
        return template.update(sql, id) > 0;
    }

    /*
    Til at udregne vores timetal fra et starttidspunkt og et sluttidspunkt har vi startet med at lave 2 strings,
    et for starttidspunktet og et for sluttidspunktet, vi bruger så substring, til at kun at få de 2 første tegn
    fra både starttidspunktet og sluttidspunktet, herefter parser vi det til doubles, nu har vi det antal timer nogen har arbejdet

    For så at sikre os, at der ikke kommer minus antal timer (i tilfælde af en vagt overskrider midnat) har vi så lavet en if statement,
    som tillægger 24 timer til, hvis sluttidspunktet er mindre end starttidspunkt, eller er det samme som starttidspunktet.

    For så at udregne minut antallet, laver vi så 2 nye strings, hvor vi gentager samme proces, dog med at vi starter fra 3. tegn
    og slutter på 5. tegn (eksclusiv 5. tegn), for så at gøre dette til timetal, dividere vi selvfølgelig med 60, for senere at lægge det til
    vores timetal og laver så til sidst en return på timetal.
     */

    public double getHoursWorked(String startTidString, String slutTidString) {
        double timetal;
        String startTidTimeString = startTidString.substring(0,2);
        String slutTidTimeString = slutTidString.substring(0,2);

        double startTidTime = Double.parseDouble(startTidTimeString);
        double slutTidTime = Double.parseDouble(slutTidTimeString);

        if(slutTidTime <= startTidTime){
            slutTidTime += 24;
        }

        timetal = slutTidTime - startTidTime;

        String startTidMinutString = startTidString.substring(3,5);
        String slutTidMinutString = slutTidString.substring(3,5);

        double startTidMinut = Double.parseDouble(startTidMinutString);
        double slutTidMinut = Double.parseDouble(slutTidMinutString);

        startTidMinut /= 60;
        slutTidMinut /= 60;

        timetal += slutTidMinut - startTidMinut;
        return timetal;
    }

}
