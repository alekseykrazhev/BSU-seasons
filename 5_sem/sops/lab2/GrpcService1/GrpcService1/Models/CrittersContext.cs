﻿using System;
using System.Collections.Generic;
using GrpcService1.Models;
using Microsoft.EntityFrameworkCore;

namespace GrpcService1.Models
{
    public sealed class CrittersContext : DbContext
    {
        public DbSet<Roster> roster { get; set; }
        public DbSet<Temp> temp { get; set; }

        public CrittersContext(DbContextOptions<CrittersContext> options)
            : base(options)
        {
            Database.EnsureCreated();
        }

        public CrittersContext()
        {
            Database.EnsureCreated();
        }
        
        protected override void OnConfiguring(DbContextOptionsBuilder optionsBuilder)
        {
            optionsBuilder.UseSqlite("Data Source=data.sqlite");
        }

        public void MoveToTemp(Player player)
        {
            Temp tempPlayer = Temp.fromBase(player);
            Add(tempPlayer);
            SaveChanges();
        }

        public void MoveToRoster(Player player)
        {
            Roster rosterPlayer = Roster.fromBase(player);
            Remove(player);
            SaveChanges();
        }

        public void MoveAllToRoster()
        {
            foreach (Temp t in temp)
            {
                MoveToRoster(t);
            }
        }

        public void SaveCritter(Player player, string playerid, int jersey, string fname, string sname, string position,
            string birtday, int weight, int height, string birthcity, string birthstater)
        {
            player.playerid = playerid;
            player.jersey = jersey;
            player.fname = fname;
            player.sname = sname;
            player.position = position;
            player.birthday = DateTime.Parse(birtday);
            player.weight = weight;
            player.height = height;
            player.birthcity = birthcity;
            player.birthstate = birthstater;
            SaveChanges();
        }

        public void ClearTemp()
        {
            Database.ExecuteSqlRaw("DELETE FROM temp");
        }

    }
}
