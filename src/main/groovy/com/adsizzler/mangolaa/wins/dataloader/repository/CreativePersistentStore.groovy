package com.adsizzler.mangolaa.wins.dataloader.repository

import com.adsizzler.mangolaa.wins.dataloader.dto.CreativeDTO
import org.springframework.data.jpa.repository.JpaRepository

/**
 * Created by ankushsharma on 29/01/18.
 */
interface CreativePersistentStore extends JpaRepository<CreativeDTO,Integer> {
}
